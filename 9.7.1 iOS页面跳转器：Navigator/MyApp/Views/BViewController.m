//
//  AboutUsViewController.m
//

#import "BViewController.h"
#import "Navigator.h"

@interface BViewController () {
    UILabel* versionLabel;
    NSString* version;
}

@end

@implementation BViewController

- (void)createFields {
    if(self.param!=nil){
        version = [self.param objectForKey: @"version"];
    }
}

- (void)destroyFields {

}

- (void)createViews {
    //1.从xib中获取View
    NSArray* list = [[NSBundle mainBundle] loadNibNamed: @"AboutUsView" owner: self options: nil];
    self.view = list.lastObject;
    
    versionLabel = (UILabel*)[self.view viewWithTag: 200];
    versionLabel.text = version;
}

- (void)destroyViews {

}

- (void)createEvents {

}

- (void)destroyEvents {
    
}

- (void)loadData {
    
}

@end
