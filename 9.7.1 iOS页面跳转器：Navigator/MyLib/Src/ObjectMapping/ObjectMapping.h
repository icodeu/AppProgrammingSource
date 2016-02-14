#import <Foundation/Foundation.h>

@interface ObjectMapping : NSObject
{
    Class _typeOfClass;
    NSMutableArray* _mappings;
}

@property (nonatomic, assign) Class typeOfClass;
@property (nonatomic, retain) NSMutableArray *mappings;

+ (ObjectMapping *)mappingForClass:(Class)typeOfClass;

- (void)converEntityFromJsonToEntity:(NSString *)from to:(NSString *)to withClass:(NSString*)className;

@end
