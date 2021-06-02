package Dijkstra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
        int M;
        Scanner scanner = new Scanner(new File("file.txt"));
        N = scanner.nextInt();
        for(int i=0; i<N; i++)
        {
            vertices.add(scanner.next());
        }
        M =scanner.nextInt();
        Pair[]ribs = new Pair[M];

        int weight[];
        weight = new int[M];
        for(int i=0; i<M; i++)
        {
            String S1 = scanner.next();
            String S2 = scanner.next();
            ribs[i] = new Pair(S1,S2);
            weight[i]=scanner.nextInt();
        }
        scanner.close();

        System.out.print("Vertices of the graph: ");
        for(int i=0; i<N; i++)
        {
            System.out.print(vertices.get(i)+" ");
        }
        System.out.println("\nRibs of the graph: ");
        for(int i=1; i<=M; i++) {
            System.out.println("Rib "+i+": from "+ribs[i-1].getL()+" to "+ribs[i-1].getR()+", weight "+weight[i-1]);
        }
        int dist[];
        dist = new int[N];

        dist[0]=0;
        int inf = 100000000;
        for(int i=1; i<N; i++)
        {
            dist[i]=inf;
        }
        PriorityQueue<Pair<Integer,String>> q = new PriorityQueue<>();
        Pair s = new Pair(0,"s");
        q.add(s);
        while(q.peek() != null) {
            Pair v;
            v = q.poll();
            String v0 = (String)v.getR();

            for (int k = 0; k < M; k++){
                if (v0.equals(ribs[k].getL())){
                if(dist[vertices.indexOf(v0)]+weight[k] < dist[vertices.indexOf(ribs[k].getR())]){
                    Integer p0 = new Integer(dist[vertices.indexOf(ribs[k].getR())]);
                    Pair p = new Pair(p0,ribs[k].getR());
                    q.remove(p);
                    dist[vertices.indexOf(ribs[k].getR())]=dist[vertices.indexOf(v0)]+weight[k];
                    Integer p1 = new Integer(dist[vertices.indexOf(ribs[k].getR())]);
                    p.setL(p1);
                    q.add(p);
                }
                else continue;
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
