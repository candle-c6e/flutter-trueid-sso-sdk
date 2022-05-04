#import "TrueSdkPlugin.h"
#if __has_include(<true_sdk/true_sdk-Swift.h>)
#import <true_sdk/true_sdk-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "true_sdk-Swift.h"
#endif

@implementation TrueSdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftTrueSdkPlugin registerWithRegistrar:registrar];
}
@end
