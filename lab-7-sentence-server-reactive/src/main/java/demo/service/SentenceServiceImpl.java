package demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.domain.Sentence;
import demo.domain.Word;
import rx.Observable;

@Service
public class SentenceServiceImpl implements SentenceService {

	@Autowired WordService wordService;

	public String buildSentence() {
		Sentence sentence = new Sentence();
		
		List<Observable<Word>> observables = createObservables();
		
		//	Use a CountDownLatch to detect when ALL of the calls are complete:
		CountDownLatch latch = new CountDownLatch(observables.size());
		
		//	Merge the 5 observables into one, so we can add a common subscriber:
		Observable.merge(observables)
			.subscribe(
				//	(Lambda) When each service call is complete, contribute its word
				//	to the sentence, and decrement the CountDownLatch:
				(word) -> {
					sentence.add(word);
					latch.countDown();
		        }
		);
		
		//	This code will wait until the LAST service call is complete:
		waitForAll(latch);

		//	Return the completed sentence:
		return sentence.toString();
	}


	/**
	 * Ultimately, we will need to wait for all 5 calls to 
	 * be completed before the sentence can be assembled.  
	 * This code waits for the last call to come back:
	 */
	private void waitForAll(CountDownLatch latch) {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private List<Observable<Word>> createObservables(){
		List<Observable<Word>> observables = new ArrayList<>();
		observables.add(wordService.getSubject());
		observables.add(wordService.getVerb());
		observables.add(wordService.getArticle());
		observables.add(wordService.getAdjective());
		observables.add(wordService.getNoun());
		return observables;
	}
}