// JSON iOS Class Generator
// Written by Bruce Bao

#import <Foundation/Foundation.h>

@class ObjectMapping;

@interface UserEntity : NSObject
{
    NSString *name;
    NSNumber *age;
}

@property (nonatomic,retain) NSString *name;
@property (nonatomic,retain) NSNumber *age;

- (ObjectMapping *)objectMapping;

@end


