package org.digvijay.lld;

import java.util.LinkedList;
import java.util.HashMap;

public class RateLimiter {
  private int timeWindow;
  private int maxRequestsAllowedInTimeWindow;
  private HashMap<Integer, LinkedList<Integer>> clientRequests;

  RateLimiter (int timeWindow, int maxRequestsAllowedInTimeWindow) {
    this.timeWindow = timeWindow;
    this.maxRequestsAllowedInTimeWindow = maxRequestsAllowedInTimeWindow;
    clientRequests = new HashMap<>();
  }

  public int isRequestAllowed(int clientId, int requestTimestamp) {
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
    RateLimiter rateLimiter = new RateLimiter(2, 1);
    System.out.println(rateLimiter.isRequestAllowed(1,1));
    System.out.println(rateLimiter.isRequestAllowed(1,2));
    System.out.println(rateLimiter.isRequestAllowed(1,3));
    System.out.println(rateLimiter.isRequestAllowed(1,4));
    System.out.println(rateLimiter.isRequestAllowed(1,5));
    System.out.println(rateLimiter.isRequestAllowed(1,6));
    System.out.println(rateLimiter.isRequestAllowed(1,7));
  }
}
