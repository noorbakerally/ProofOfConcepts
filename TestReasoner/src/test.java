import java.io.File;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
public class test {
	public static void main(String [] args) throws OWLOntologyCreationException{
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		//the absolute path for the ontology
		String userHome = System.getProperty("user.home");
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(userHome+"/Dropbox/TaggingCaseStudy/Programs/program/info/ma1.owl"));
		OWLDataFactory factory = manager.getOWLDataFactory();
		PrefixManager pm = new DefaultPrefixManager(ontology.getOntologyID().getOntologyIRI().toString());
		OWLReasoner reasoner=new Reasoner(ontology);
		reasoner.flush();
		OWLClassExpression query = factory.getOWLObjectIntersectionOf(factory.getOWLClass("#belgique", pm),factory.getOWLClass("#celebration", pm));
		System.out.println(reasoner.getInstances(query,true));
		reasoner.flush();
		System.out.println(reasoner.getInstances(query,true));
	}
}
