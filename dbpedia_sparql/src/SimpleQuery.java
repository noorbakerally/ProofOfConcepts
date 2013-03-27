import com.hp.hpl.jena.query.*;
public class SimpleQuery {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		        String s2 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?label " +
							"WHERE { " +
							"<http://dbpedia.org/resource/Quatre_Bornes> <http://dbpedia.org/ontology/country> ?y ." +
							"?y rdfs:label ?label ." +
							"FILTER (LANG(?label) = 'en')" +
							"}";
		        Query query = QueryFactory.create(s2); //s2 = the query above
		        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", query );
		        ResultSet results = qExe.execSelect();
		        ResultSetFormatter.out(System.out, results, query) ;
		}
	}
