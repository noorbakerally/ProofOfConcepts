import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import edu.mit.jwi.morph.WordnetStemmer;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.JiangAndConrath;
import edu.sussex.nlp.jws.Resnik;


public class TestClasses {
	IDictionary dict;
	public TestClasses(){
		
		URL url;
		try {
			url = new URL ( "file" , null , "files/ontologies/dictionary/3.0/dict" );
			dict = new Dictionary ( url ) ;
			dict.open();
		} catch (MalformedURLException e) {
			System.out.println("Problem with dictionary in ThesaurusBasedQueryProcessor Class");
			e.printStackTrace();
		}
	}	
	
	void getAllSynHypo(String currentWord){
		IIndexWord synsets = dict.getIndexWord(currentWord, POS.NOUN);
		for (IWordID wordId:synsets.getWordIDs()){
			IWord words = dict.getWord(wordId);
			System.out.println("Synset"+synsets.getWordIDs().indexOf(wordId)+":");
			for ( IWord w : words.getSynset().getWords() ){
				System . out . println ( w . getLemma () ) ;
			}
			System.out.println();
		}
		
		IIndexWord idxWord = dict.getIndexWord (currentWord, POS.NOUN );
		
		System.out.println("Hyponyms:");
		//expanding the query
		for (IWordID wordID:idxWord.getWordIDs()){	
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
					
			List < ISynsetID > hyponyms =synset.getRelatedSynsets(Pointer.HYPONYM);
			for ( ISynsetID sid : hyponyms ) {
				List < IWord > words = dict.getSynset(sid).getWords();
				for ( Iterator <IWord> i = words.iterator();i.hasNext();) {
					System.out.print(" "+i.next().getLemma()+" ");
				}
			}
			System.out.println();
		}
		
		
	}
	
	
	public void printAllSynonyms(String word){
		//loading the dictionary
		URL url;
		try {
			url = new URL ( "file" , null , "files/ontologies/dictionary/3.0/dict" );
			dict = new Dictionary ( url ) ;
			dict.open();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//get the synsets
		IIndexWord synsets = dict.getIndexWord(word, POS.NOUN);
		//iterate over the synsets
		for (IWordID wordId:synsets.getWordIDs()){
			System.out.println("Synset #"+synsets.getWordIDs().indexOf(wordId));
			IWord words = dict.getWord(wordId);
			for ( IWord w : words.getSynset().getWords() ){
				System.out.print(w.getLemma()+" , ");
			}
			System.out.println();
			System.out.println();
		}
	}
	void testWordNetSimilarity(){
		
		JWS	ws = new JWS("files/ontologies/dictionary", "3.0");
		
		JiangAndConrath jcn = ws.getJiangAndConrath();
		Resnik res = ws.getResnik();
		
		TreeMap<String, Double> scores1 = ws.getLeacockAndChodorow().lch("test", "mental_testing", "n");
		for(Entry<String, Double> e: scores1.entrySet())
		    System.out.println(e.getKey() + "\t" + e.getValue());
	}
	
	void testWordNetStemming(String text){
		WordnetStemmer wordnetStemmer = new WordnetStemmer(dict);
		//wordnetStemmer.findStems("countries", POS.NOUN);
		System.out.println(wordnetStemmer.findStems(text));
	}
	
}
