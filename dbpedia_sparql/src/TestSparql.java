import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class TestSparql {
	String prefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " +
			"PREFIX : <http://dbpedia.org/resource/> " +
			"PREFIX dbpedia2: <http://dbpedia.org/property/> " +
			"PREFIX dbpedia: <http://dbpedia.org/> ";
	
	public void testOnLocalFile() throws IOException{
		// Open the bloggers RDF graph from the filesystem
		InputStream in = new FileInputStream(new File("/home/noor/TBCMEWorkspace/bbc/index.rdf"));

		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createOntologyModel();
		model.read(in,null); // null base URI, since model URIs are absolute
		in.close();

		/*
		// Create a new query
		String queryString = "PREFIX wo:<http://purl.org/ontology/wo/>" +
			" SELECT * " +
			" WHERE { " +
			" ?subject wo:kingdom ?object . " +
			" } ";
		 */
		String queryString = " SELECT * " +
	 			" WHERE { " +
	 			" ?y rdf:type ?type . " +
	 			" ?y rdfs:label ?label " +
	 			" filter regex(?label,'wolf','i') " +
	 			" } ";
		queryString = prefix + queryString ;
		Query query = QueryFactory.create(queryString);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// Output query results	
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();
	}
}
