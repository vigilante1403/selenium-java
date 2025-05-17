package pages;

public class NestedFramePage {
    public void open(){
        utils.Browser.visit("https://the-internet.herokuapp.com/nested_frames");
    }

    public void switchToFrame(String frameName){
        utils.Browser.switchToFrame(frameName);
    }

    public void switchToFrame(int frameIndex){
        utils.Browser.switchToFrame(frameIndex);
    }

    public void switchToParentFrame(){
        utils.Browser.switchToParentFrame();
    }

    public void switchToDefaultContent(){
        utils.Browser.switchToDefaultContent();
    }
}
