package Dijkstra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Graph
{
    private int numVertices;
    private LinkedList<Pair<Integer,Integer>> adjLists[];

    Graph(int vertices)
    {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList();
    }

    void addEdge(int from, int to, int weight)
    {
        Pair p0 = new Pair(to, weight);
        adjLists[from].add(p0);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public LinkedList<Pair<Integer,Integer>>[] getAdjLists() {
        return adjLists;
    }
}

    class Pair<L,R> implements Comparable<Pair> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }

    public Pair() {
    }

    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }

    @Override
    public int compareTo(Pair o) {
        return 0;
    }
}

public class Dijkstra {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int N;

        ArrayList<String> vertices = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("file.txt"));
        N = scanner.nextInt();
        Graph G = new Graph(N);
        for(int i=0; i<N; i++)
        {
            vertices.add(scanner.next());
        }


        for(int i=0; i<N-1; i++)
        {
            int k = scanner.nextInt();
            for (int j = 0; j<k; j++) {
                String S1 = scanner.next();
                int w1 = scanner.nextInt();
                G.addEdge(i,vertices.indexOf(S1),w1);
            }

        }
        scanner.close();



        System.out.print("Vertices of the graph: ");
        for(int i=0; i<N; i++)
        {
            System.out.print(vertices.get(i)+" ");
        }

        System.out.print("\nAdjacency list: ");
        for(int i=0; i<N-1; i++)
        {
            System.out.print("\nVertices adjacent to "+vertices.get(i)+": ");
            for(int j=0;j<G.getAdjLists()[i].size();j++)
                System.out.print(vertices.get(G.getAdjLists()[i].get(j).getL())+", weight "+G.getAdjLists()[i].get(j).getR()+"; ");
            System.out.print("\n");
        }

        int dist[];
        dist = new int[N];

        dist[0]=0;
        int inf = 100000000;
        for(int i=1; i<N; i++)
        {
            dist[i]=inf;
        }
        PriorityQueue<Pair<Integer,Integer>> q = new PriorityQueue<>();
        Pair s = new Pair(0,vertices.indexOf("s"));
        q.add(s);


        while(q.peek() != null) {
            Pair v;
            v = q.poll();
            int v0 = (int)v.getR();
            System.out.println(v0);

            for(int i=0; i<G.getAdjLists()[v0].size();i++){

                if(dist[v0]+G.getAdjLists()[v0].get(i).getR() < dist[G.getAdjLists()[v0].get(i).getL()]){

                    Integer p0 = new Integer(dist[G.getAdjLists()[v0].get(i).getL()]);

                    Pair p = new Pair(p0,G.getAdjLists()[v0].get(i).getL());
                    q.remove(p);
                    dist[G.getAdjLists()[v0].get(i).getL()]=dist[v0]+G.getAdjLists()[v0].get(i).getR();
                    Integer p1 = new Integer(dist[G.getAdjLists()[v0].get(i).getL()]);
                    p.setL(p1);
                    q.add(p);
                }
                else continue;

            }
        }
        System.out.println("Vertices of the graph with distances from s : ");
        for(int i=0; i<N; i++)
        {
            System.out.println(vertices.get(i)+" - distance "+dist[i]);
        }
        in.close();
    }
}
