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
with Diagram('ddrsystemArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxddrsys', graph_attr=nodeattr):
          radar=Custom('radar','./qakicons/symActorWithobjSmall.png')
          mind=Custom('mind','./qakicons/symActorWithobjSmall.png')
     radar >> Edge( label='radar', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sys >> Edge( label='radar', **evattr, decorate='true', fontcolor='darkgreen') >> mind
diag
