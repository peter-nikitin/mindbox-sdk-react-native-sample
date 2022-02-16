//
//  NotificationService.swift
//  MindboxNotificationServiceExtension
//
//  Created by Никитин Петр on 16.02.2022.
//

import UserNotifications
import MindboxNotifications

class NotificationService: UNNotificationServiceExtension {
  
  lazy var mindboxService = MindboxNotificationService()
  
  var contentHandler: ((UNNotificationContent) -> Void)?
  var bestAttemptContent: UNMutableNotificationContent?
  
  var isMindbox = false;
  
  override func didReceive(_ request: UNNotificationRequest, withContentHandler contentHandler: @escaping (UNNotificationContent) -> Void) {
    
    if (request.content.userInfo["uniqueKey"] as? String != nil) {
      isMindbox = true
    }
    
    if(isMindbox) {
      mindboxService.didReceive(request, withContentHandler: contentHandler)
    } else {
      // handle Push with your own solituin
    }
  }
  
  
  override func serviceExtensionTimeWillExpire() {
    // Called just before the extension will be terminated by the system.
    // Use this as an opportunity to deliver your "best attempt" at modified content, otherwise the original push payload will be used.
    
    if (isMindbox) {
      mindboxService.serviceExtensionTimeWillExpire()
    } else {
      // handle Push with your own solituin
    }
    
  }
}
