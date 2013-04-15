import lxml.etree as le
import sys
if (sys.argv[1]=="filename"):
	quit()
f = open(sys.argv[1],'r')
doc=le.parse(f)
namespaces = { 
    "rdf":"http://www.w3.org/2000/01/rdf-schema#",
    "dc":"http://purl.org/dc/terms/",
    "foaf":"http://xmlns.com/foaf/0.1/",
    "po":"http://purl.org/ontology/po/",
    "wo":"http://purl.org/ontology/wo/", "owl":"http://www.w3.org/2002/07/owl#" }

unused = ["//foaf:depiction","//dc:description","//po:Clip","//foaf:Document","//foaf:Image","//foaf:thumbnail","//wo:collection","//wo:Collection","//owl:AnnotationAssertion","owl:AnnotationPropertyDomain"]

for x in unused:
	for elem in doc.xpath(x, namespaces=namespaces):
	    parent=elem.getparent().remove(elem)

new_file = open(sys.argv[1], "w")
new_file.write(le.tostring(doc))
new_file.close()
