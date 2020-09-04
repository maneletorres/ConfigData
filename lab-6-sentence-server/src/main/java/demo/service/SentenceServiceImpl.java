package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounClient;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	private VerbClient verbClient;
	private SubjectClient subjectClient;
	private ArticleClient articleClient;
	private AdjectiveClient adjectiveClient;
	private NounClient nounClient;

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		String sentence = "There was a problem assembling the sentence!";
		sentence =  
			String.format("%s %s %s %s %s.",
				subjectClient.getWord().getString(),
				verbClient.getWord().getString(),
				articleClient.getWord().getString(),
				adjectiveClient.getWord().getString(),
				nounClient.getWord().getString() );
		return sentence;
	}

	@Autowired
	public void setVerbService(VerbClient verbClient) {
		this.verbClient = verbClient;
	}

	@Autowired
	public void setSubjectService(SubjectClient subjectClient) {
		this.subjectClient = subjectClient;
	}

	@Autowired
	public void setArticleService(ArticleClient articleClient) {
		this.articleClient = articleClient;
	}

	@Autowired
	public void setAdjectiveService(AdjectiveClient adjectiveClient) {
		this.adjectiveClient = adjectiveClient;
	}
	
	@Autowired
	public void setNounClient(NounClient nounClient) {
		this.nounClient = nounClient;
	}
}