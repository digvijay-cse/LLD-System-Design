package org.digvijay.lld;

import java.util.LinkedList;
import java.util.HashMap;

public class RaterLimiter {
  int timeWindow;
  int maxRequestsAllowedInTimeWindow;
  HashMap<Integer, LinkedList<Integer>> clientRequests;

  RaterLimiter (int timeWindow, int maxRequestsAllowedInTimeWindow) {
    this.timeWindow = timeWindow;
    this.maxRequestsAllowedInTimeWindow = maxRequestsAllowedInTimeWindow;
    clientRequests = new HashMap<>();
  }

  public int isAllowed(int clientId, int requestTimestamp) {
    clientRequests.putIfAbsent(clientId, new LinkedList<>());
    LinkedList<Integer> linkedList = clientRequests.get(clientId);

    while(!linkedList.isEmpty() && linkedList.peek() <= requestTimestamp - timeWindow) {
      linkedList.poll();
    }

    if (linkedList.size() < maxRequestsAllowedInTimeWindow) {
      linkedList.offer(requestTimestamp);
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