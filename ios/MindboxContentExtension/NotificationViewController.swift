//
//  NotificationViewController.swift
//  MindboxContentExtension.NotificationViewController
//
//  Created by Никитин Петр on 16.02.2022.
//

import UIKit
import UserNotifications
import UserNotificationsUI
import MindboxNotifications

class NotificationViewController: UIViewController, UNNotificationContentExtension {
  
  lazy var mindboxService = MindboxNotificationService()
  
  func didReceive(_ notification: UNNotification) {
    mindboxService.didReceive(notification: notification, viewController: self, extensionContext: extensionContext)
  }
  
}
