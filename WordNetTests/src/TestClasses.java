import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.POS;
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
		} catch (MalformedURLException e) {
			System.out.println("Problem with dictionary in ThesaurusBasedQueryProcessor Class");
			e.printStackTrace();
		}
	}	
	void testWordNetSimilarity(){
		dict.open();
		JWS	ws = new JWS("files/ontologies/dictionary", "3.0");
		
		JiangAndConrath jcn = ws.getJiangAndConrath();
		Resnik res = ws.getResnik();
		
		TreeMap<String, Double> scores1 = ws.getLeacockAndChodorow().lch("apple", "computer", "n");
		for(Entry<String, Double> e: scores1.entrySet())
		    System.out.println(e.getKey() + "\t" + e.getValue());
	}
	
	void testWordNetStemming(){
		WordnetStemmer wordnetStemmer = new WordnetStemmer(dict);
		wordnetStemmer.findStems("countries", POS.NOUN);
		System.out.println(wordnetStemmer.findStems("countries", POS.NOUN));
	}
}
