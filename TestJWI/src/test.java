import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.TreeMap;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.sussex.nlp.jws.JWS;
public class test {
	public static void main(String [] args) throws IOException{
		URL url = new URL ( "file" , null , "dictionary/3.0/dict" );
		IDictionary dict = new Dictionary ( url ) ;
		dict.open () ;
		
		// look up first sense of the word " dog "
		IIndexWord idxWord = dict . getIndexWord ( "UK" , POS.NOUN ) ;
		IWordID wordID = idxWord . getWordIDs () . get (0) ; // 1 st meaning
		List <IWordID> wordIDs = idxWord.getWordIDs();
	
		
		
		for (IWordID wordID1:wordIDs){
			IWord word = dict.getWord(wordID1) ;
			ISynset synset = word.getSynset () ;
			// iterate over words associated with the synset
			for ( IWord w : synset.getWords())
				System.out.print(w.getLemma()+" ") ;	
			System.out.println();
		}
		
		/*
		
		JWS ws= new JWS ("dictionary", "3.0");
		
		
		TreeMap <String,Double> scores1 = ws.getAdaptedLesk().lesk("look", "look", "n");
		
		System.out.println(scores1.get(scores1.descendingKeySet().last()));
			
		
		for (String s:scores1.keySet())
			System.out.println(s+"\t"+scores1.get(s));
		
		
		
		IWord word = dict.getWord(wordID) ;
		ISynset synset = word.getSynset () ;
		// iterate over words associated with the synset
		for ( IWord w : synset.getWords())
			System.out.println(w.getLemma()) ;	
		*/
		
	}
}
