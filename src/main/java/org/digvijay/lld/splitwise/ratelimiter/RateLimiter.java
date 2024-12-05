package org.digvijay.lld;

import java.util.*;

public class RateLimiter {
  int timeWindow;
  int maxRequests;
  HashMap<Integer, LinkedList<Integer>> clientRequests;

  RaterLimiter (int timeWindow, int maxRequests) {
    this.timeWindow = timeWindow;
    this.maxRequests = maxRequests;
    clientRequests = new HashMap<>();
  }

  public int isAllowed(int clientId, int timestamp) {
    clientRequests.putIfAbsent(clientId, new LinkedList<>());
    LinkedList<Integer> linkedList = clientRequests.get(clientId);

    while(!linkedList.isEmpty() && linkedList.peek() <= timestamp - timeWindow) {
      linkedList.poll();
    }

    if (linkedList.size() < maxRequests) {
      linkedList.offer(timestamp);
      return 200;
    } else {
      return 429;
    }
  }

  public static void main(String[] args) {
    RaterLimiter raterLimiter = new RaterLimiter(2, 1);
    System.out.println(raterLimiter.isAllowed(1,1));
    System.out.println(raterLimiter.isAllowed(1,2));
    System.out.println(raterLimiter.isAllowed(1,3));
    System.out.println(raterLimiter.isAllowed(1,4));
    System.out.println(raterLimiter.isAllowed(1,5));
    System.out.println(raterLimiter.isAllowed(1,6));
    System.out.println(raterLimiter.isAllowed(1,7));
  }
}
