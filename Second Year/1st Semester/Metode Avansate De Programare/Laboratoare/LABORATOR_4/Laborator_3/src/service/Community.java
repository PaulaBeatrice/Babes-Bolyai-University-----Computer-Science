package service;

import domain.Friendship;
import domain.User;

import java.util.*;

public class Community {
    Network network;
    HashMap<Long, List<Long>> adjacencyList;

    public Community(Network network) {
        this.network = network;
        adjacencyList = new HashMap<Long, List<Long>>();
        for (User e : network.getUsers()) {
            List<Long> friends = new ArrayList<>();
            for (Friendship fr : network.getFriendships()) {
                if (fr.getIdUser1().equals(e.getId()))
                    friends.add(fr.getIdUser2());
                if (fr.getIdUser2().equals(e.getId()))
                    friends.add(fr.getIdUser1());
            }
            if (friends.size() != 0)
                this.adjacencyList.put(e.getId(), friends);
        }
    }

    void DFSUtil(Long v, HashMap<Long, Boolean> visited) {
        visited.put(v, true);
        System.out.print(v + " ");
        if (adjacencyList.containsKey(v)) {
            for (Long x : adjacencyList.get(v)) {
                if (!visited.containsKey(x))
                    DFSUtil(x, visited);
            }
        }
    }

    public void connectedComponents() {
        adjacencyList = new HashMap<Long, List<Long>>();
        for (User e : network.getUsers()) {
            List<Long> friends = new ArrayList<>();
            for (Friendship fr : network.getFriendships()) {
                if (fr.getIdUser1().equals(e.getId()))
                    friends.add(fr.getIdUser2());
                if (fr.getIdUser2().equals(e.getId()))
                    friends.add(fr.getIdUser1());
            }
            if (friends.size() != 0)
                this.adjacencyList.put(e.getId(), friends);
        }
        List<Long> ids = new ArrayList<>();
        for (User u : network.getUsers())
            ids.add(u.getId());

        HashMap<Long, Boolean> visited = new HashMap<Long, Boolean>();
        for (Long v : ids) {
            if (!visited.containsKey(v)) {
                DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    void topologicalSortUtil(Long v, HashMap<Long,Boolean> visited, Stack<Long> stack)
    {
        visited.put(v, true);
        if(adjacencyList.get(v) != null)
        {
            for (Long x : adjacencyList.get(v)) {
                if (!visited.containsKey(x))
                    topologicalSortUtil(x, visited, stack);
            }
        }
        stack.push(v);
    }

    int longestPath(Long s)
    {
        Stack<Long> stack = new Stack<Long>();
        HashMap<Long, Integer> dist = new HashMap<Long, Integer>();
        HashMap<Long,Boolean> visited = new HashMap<Long, Boolean>();

        List<Long> ids = new ArrayList<>();
        for(User u:network.getUsers())
            ids.add(u.getId());

        for (Long v: ids)
        {
            if (!visited.containsKey(v))
            {
                topologicalSortUtil(v, visited, stack);
            }
        }

        for (Long v: ids) {
            if (v.equals(s))
                dist.put(s, 0);
            else
                dist.put(v, Integer.MIN_VALUE);
        }

        while (!stack.isEmpty())
        {
            Long u = stack.peek();
            stack.pop();
            if (dist.get(u) != Integer.MIN_VALUE)
            {
                if(this.adjacencyList.get(u) != null)
                { //System.out.println(u + "=" + adjacencyList.get(u));
                    for (Long x : adjacencyList.get(u)) {
                        if (dist.get(x) < dist.get(u) + 1 && dist.get(x) != 0 && stack.contains(x)) {
                            dist.put(x, dist.get(u) + 1);
                            //  System.out.println("x=" + x + " u= " + u + '\n');
                        }
                    }
                }
            }
        }
//        for (Long x: ids)
//            if(dist.get(x) == Integer.MIN_VALUE)
//                System.out.print(x +"-INF ");
//            else
//                System.out.print(x+ "-" + dist.get(x) + " ");
//        System.out.println();
        int maxim = 0;
        for(Long x:ids)
            if(dist.get(x) > maxim)
                maxim = dist.get(x);
        return maxim;
    }

    public void getLongest()
    {
        adjacencyList = new HashMap<Long, List<Long>>();
        for (User e : network.getUsers()) {
            List<Long> friends = new ArrayList<>();
            for (Friendship fr : network.getFriendships()) {
                if (fr.getIdUser1().equals(e.getId()))
                    friends.add(fr.getIdUser2());
                if (fr.getIdUser2().equals(e.getId()))
                    friends.add(fr.getIdUser1());
            }
            if (friends.size() != 0)
                this.adjacencyList.put(e.getId(), friends);
        }
        int maxim = 0;
        Long id = 0L;
        List<Long> ids = new ArrayList<>();
        for(User u:network.getUsers())
            ids.add(u.getId());
        for(Long u: ids)
            if(longestPath(u) > maxim)
            {
                maxim = longestPath(u);
                id = u;
            }
        HashMap<Long, Boolean> visited = new HashMap<Long, Boolean>();
        DFSUtil(id, visited);
        System.out.println();
    }
}















/*
    //        longestPath(5L);  pt 5->6 dist e 1
//        longestPath(6L);  pt 6->5 dist e INF

//        longestPath(1L);  pt 1->3 dist e 1
//        longestPath(3L);  pt 3->1 dist e INF





    public void DFSUtil2(Long v, HashMap<Long,Boolean> visited, ArrayList<Long> al)
    {

        visited.put(v,true);
        al.add(v);
        if (adjacencyList.containsKey(v)) {
            for (Long x : adjacencyList.get(v)) {
                if (!visited.containsKey(x))
                    DFSUtil2(x, visited, al);
            }
        }

    }

    public void DFS()
    {
        ArrayList<ArrayList<Long> > components
                = new ArrayList<>();
        ArrayList<Long>  components1
                = new ArrayList<>();
        HashMap<Long, Boolean> visited = new HashMap<Long, Boolean>();
        int max=-1;
        List<Long> listIds = new ArrayList<Long>();
        for(User u: network.getUsers())
            listIds.add(u.getId());
        for (Long id:listIds) {
            ArrayList<Long> al = new ArrayList<>();
            if (!visited.containsKey(id)) {
                DFSUtil2(id, visited, al);
                components.add(al);
            }
        }
        System.out.println("The longest community:");
        for(ArrayList<Long> a:components){
            System.out.println(a);
            if (a.size()>max) {
                max = a.size();
                components1=a;}
        }
        String rez = "";
        for (Object a:components1){
            rez = rez + a.toString() + " ";
        }
        System.out.println(rez);
    }
}
 */



