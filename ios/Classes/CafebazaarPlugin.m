#import "CafebazaarPlugin.h"
#if __has_include(<cafebazaar/cafebazaar-Swift.h>)
#import <cafebazaar/cafebazaar-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "cafebazaar-Swift.h"
#endif

@implementation CafebazaarPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftCafebazaarPlugin registerWithRegistrar:registrar];
}
@end
