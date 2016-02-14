#import <Foundation/Foundation.h>

@interface ObjectMappingEntity : NSObject
{
    NSString *_from;
    NSString *_to;
    Class _typeOfClass;
    BOOL _isSimpleType;
}
@property (nonatomic, retain) NSString *from;
@property (nonatomic, retain) NSString *to;
@property (nonatomic, assign) Class typeOfClass;
@property (nonatomic, assign) BOOL isSimpleType;

@end
