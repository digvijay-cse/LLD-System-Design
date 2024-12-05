package org.digvijay.lld.ratelimiter;

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
    // A.
    // 1 requests in every 2 seconds.
    RateLimiter rateLimitTier_A = new RateLimiter(2, 1);
    System.out.println(rateLimitTier_A.isRequestAllowed(1,1)); // 200
    System.out.println(rateLimitTier_A.isRequestAllowed(1,2)); // 429
    System.out.println(rateLimitTier_A.isRequestAllowed(2,2)); // 200
    System.out.println(rateLimitTier_A.isRequestAllowed(1,3)); // 200
    // B.
    // 1 requests in every 5 seconds.
    RateLimiter rateLimitTier_B = new RateLimiter(5, 1);
    System.out.println(rateLimitTier_B.isRequestAllowed(2,1)); // 200
    System.out.println(rateLimitTier_B.isRequestAllowed(2,5)); // 429
    System.out.println(rateLimitTier_B.isRequestAllowed(3,3)); // 200
    // C.
    // 3 requests in every 2 seconds.
    RateLimiter rateLimitTier_C = new RateLimiter(2, 3);
    System.out.println(rateLimitTier_C.isRequestAllowed(3,5)); // 200
    System.out.println(rateLimitTier_C.isRequestAllowed(3,6)); // 200
    System.out.println(rateLimitTier_C.isRequestAllowed(3,6)); // 200
    System.out.println(rateLimitTier_C.isRequestAllowed(3,6)); // 429
    System.out.println(rateLimitTier_C.isRequestAllowed(4,6)); // 200
  }
}
