#import <Foundation/Foundation.h>

@class ObjectMapping;

@interface ObjectMappingLoader : NSObject

+ (id)loadObjectWithClassName:(NSString *)className andData:(id)jsonData;

@end
