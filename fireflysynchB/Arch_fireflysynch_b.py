### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('fireflysynch_bArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxfireflysynch', graph_attr=nodeattr):
          creator=Custom('creator','./qakicons/symActorWithobjSmall.png')
          firefly=Custom('firefly','./qakicons/symActorDynamicWithobj.png')
          sonarmock=Custom('sonarmock','./qakicons/symActorWithobjSmall.png')
     with Cluster('ctxgrid', graph_attr=nodeattr):
          griddisplay=Custom('griddisplay(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='minore', **evattr, decorate='true', fontcolor='darkgreen') >> firefly
     sys >> Edge( label='maggiore', **evattr, decorate='true', fontcolor='darkgreen') >> firefly
     sys >> Edge( label='go', **evattr, decorate='true', fontcolor='darkgreen') >> firefly
     sonarmock >> Edge( label='minore', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='go', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarmock >> Edge( label='maggiore', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     firefly >> Edge(color='blue', style='solid',  decorate='true', label='<cellstate &nbsp; >',  fontcolor='blue') >> griddisplay
diag
