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
    LinkedList<Integer> requests = clientRequests.get(clientId);

    while(!requests.isEmpty() && requests.peek() <= requestTimestamp - timeWindow) {
      requests.poll();
    }

    if (requests.size() < maxRequestsAllowedInTimeWindow) {
      requests.offer(requestTimestamp);
      return 200;
    } else {
      return 429;
    }
  }

  public static void main(String[] args) {
    // 1 requests in every 2 seconds.
    RateLimiter rateLimiterTier = new RateLimiter(2, 1);
    System.out.println(rateLimiterTier.isRequestAllowed(1,1)); // 200
    System.out.println(rateLimiterTier.isRequestAllowed(1,2)); // 429
    System.out.println(rateLimiterTier.isRequestAllowed(1,3)); // 200
    System.out.println(rateLimiterTier.isRequestAllowed(2,4)); // 200
    System.out.println(rateLimiterTier.isRequestAllowed(2,5)); // 429
    System.out.println(rateLimiterTier.isRequestAllowed(2,6)); // 200
    System.out.println(rateLimiterTier.isRequestAllowed(3,7)); // 200
  }
}
