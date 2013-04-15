import lxml.etree as le

with open('sample.xml','r') as f:
    doc=le.parse(f)
    for elem in doc.xpath('//*[attribute::lang]'):
        if elem.attrib['lang']=='en':
            elem.attrib.pop('lang')
        else:
            parent=elem.getparent()
            parent.remove(elem)
    print(le.tostring(doc))
