"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T14:26:57.620 */
var CoordinatorlayoutTypes;
(function (CoordinatorlayoutTypes) {
    var LayoutAnchorGravityFlagsEnum;
    (function (LayoutAnchorGravityFlagsEnum) {
        LayoutAnchorGravityFlagsEnum["bottom"] = "bottom";
        LayoutAnchorGravityFlagsEnum["center"] = "center";
        LayoutAnchorGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        LayoutAnchorGravityFlagsEnum["center_vertical"] = "center_vertical";
        LayoutAnchorGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        LayoutAnchorGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        LayoutAnchorGravityFlagsEnum["end"] = "end";
        LayoutAnchorGravityFlagsEnum["fill"] = "fill";
        LayoutAnchorGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        LayoutAnchorGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        LayoutAnchorGravityFlagsEnum["left"] = "left";
        LayoutAnchorGravityFlagsEnum["right"] = "right";
        LayoutAnchorGravityFlagsEnum["start"] = "start";
        LayoutAnchorGravityFlagsEnum["top"] = "top";
    })(LayoutAnchorGravityFlagsEnum = CoordinatorlayoutTypes.LayoutAnchorGravityFlagsEnum || (CoordinatorlayoutTypes.LayoutAnchorGravityFlagsEnum = {}));
    var LayoutDodgeInsetEdgesFlagsEnum;
    (function (LayoutDodgeInsetEdgesFlagsEnum) {
        LayoutDodgeInsetEdgesFlagsEnum["all"] = "all";
        LayoutDodgeInsetEdgesFlagsEnum["bottom"] = "bottom";
        LayoutDodgeInsetEdgesFlagsEnum["end"] = "end";
        LayoutDodgeInsetEdgesFlagsEnum["left"] = "left";
        LayoutDodgeInsetEdgesFlagsEnum["none"] = "none";
        LayoutDodgeInsetEdgesFlagsEnum["right"] = "right";
        LayoutDodgeInsetEdgesFlagsEnum["start"] = "start";
        LayoutDodgeInsetEdgesFlagsEnum["top"] = "top";
    })(LayoutDodgeInsetEdgesFlagsEnum = CoordinatorlayoutTypes.LayoutDodgeInsetEdgesFlagsEnum || (CoordinatorlayoutTypes.LayoutDodgeInsetEdgesFlagsEnum = {}));
    var LayoutInsetEdgeEnum;
    (function (LayoutInsetEdgeEnum) {
        LayoutInsetEdgeEnum["bottom"] = "bottom";
        LayoutInsetEdgeEnum["end"] = "end";
        LayoutInsetEdgeEnum["left"] = "left";
        LayoutInsetEdgeEnum["none"] = "none";
        LayoutInsetEdgeEnum["right"] = "right";
        LayoutInsetEdgeEnum["start"] = "start";
        LayoutInsetEdgeEnum["top"] = "top";
    })(LayoutInsetEdgeEnum = CoordinatorlayoutTypes.LayoutInsetEdgeEnum || (CoordinatorlayoutTypes.LayoutInsetEdgeEnum = {}));
})(CoordinatorlayoutTypes = exports.CoordinatorlayoutTypes || (exports.CoordinatorlayoutTypes = {}));
// elements
exports.CoordinatorLayout = function (attributes, children) {
    return element_1.element('coordinatorLayout', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvY29vcmRpbmF0b3JsYXlvdXQudHMiLCJtYXBwaW5ncyI6Ijs7QUFBQSxxQ0FBaUQ7QUFHakQsUUFBUTtBQUNSLHlDQUF5QztBQUN6QyxJQUFpQixzQkFBc0IsQ0ErQ3RDO0FBL0NELFdBQWlCLHNCQUFzQjtJQVlyQyxJQUFZLDRCQWVYO0lBZkQsV0FBWSw0QkFBNEI7UUFDdEMsaURBQWlCLENBQUE7UUFDakIsaURBQWlCLENBQUE7UUFDakIsdUVBQXVDLENBQUE7UUFDdkMsbUVBQW1DLENBQUE7UUFDbkMsbUVBQW1DLENBQUE7UUFDbkMsK0RBQStCLENBQUE7UUFDL0IsMkNBQVcsQ0FBQTtRQUNYLDZDQUFhLENBQUE7UUFDYixtRUFBbUMsQ0FBQTtRQUNuQywrREFBK0IsQ0FBQTtRQUMvQiw2Q0FBYSxDQUFBO1FBQ2IsK0NBQWUsQ0FBQTtRQUNmLCtDQUFlLENBQUE7UUFDZiwyQ0FBVyxDQUFBO0lBQ2IsQ0FBQyxFQWZXLDRCQUE0QixHQUE1QixtREFBNEIsS0FBNUIsbURBQTRCLFFBZXZDO0lBQ0QsSUFBWSw4QkFTWDtJQVRELFdBQVksOEJBQThCO1FBQ3hDLDZDQUFXLENBQUE7UUFDWCxtREFBaUIsQ0FBQTtRQUNqQiw2Q0FBVyxDQUFBO1FBQ1gsK0NBQWEsQ0FBQTtRQUNiLCtDQUFhLENBQUE7UUFDYixpREFBZSxDQUFBO1FBQ2YsaURBQWUsQ0FBQTtRQUNmLDZDQUFXLENBQUE7SUFDYixDQUFDLEVBVFcsOEJBQThCLEdBQTlCLHFEQUE4QixLQUE5QixxREFBOEIsUUFTekM7SUFDRCxJQUFZLG1CQVFYO0lBUkQsV0FBWSxtQkFBbUI7UUFDN0Isd0NBQWlCLENBQUE7UUFDakIsa0NBQVcsQ0FBQTtRQUNYLG9DQUFhLENBQUE7UUFDYixvQ0FBYSxDQUFBO1FBQ2Isc0NBQWUsQ0FBQTtRQUNmLHNDQUFlLENBQUE7UUFDZixrQ0FBVyxDQUFBO0lBQ2IsQ0FBQyxFQVJXLG1CQUFtQixHQUFuQiwwQ0FBbUIsS0FBbkIsMENBQW1CLFFBUTlCO0FBQ0gsQ0FBQyxFQS9DZ0Isc0JBQXNCLEdBQXRCLDhCQUFzQixLQUF0Qiw4QkFBc0IsUUErQ3RDO0FBQ0QsV0FBVztBQUNFLFFBQUEsaUJBQWlCLEdBQUcsVUFDL0IsVUFBdUYsRUFDdkYsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLG1CQUFtQixFQUFFLFVBQVUsRUFBRSxRQUFRLENBQUMsQ0FBQztBQUM1RCxDQUFDLENBQUMiLCJuYW1lcyI6W10sInNvdXJjZXMiOlsiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvY29vcmRpbmF0b3JsYXlvdXQudHMiXSwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgRWxlbWVudE5vZGUsIGVsZW1lbnQgfSBmcm9tICcuL2VsZW1lbnQnO1xuaW1wb3J0IHsgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzIH0gZnJvbSAnLi9sYXlvdXRwYXJhbXMnO1xuaW1wb3J0IHsgTWFpblR5cGVzIH0gZnJvbSBcIi4vbWFpblwiO1xuLy8gdHlwZXNcbi8qIGdlbmVyYXRlZCBAIDIwMTgtMTEtMTJUMTQ6MjY6NTcuNjIwICovXG5leHBvcnQgbmFtZXNwYWNlIENvb3JkaW5hdG9ybGF5b3V0VHlwZXMge1xuICBleHBvcnQgaW50ZXJmYWNlIENvb3JkaW5hdG9yTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIE1haW5UeXBlcy5WaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIENvb3JkaW5hdG9yTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyBleHRlbmRzIE1haW5UeXBlcy5WaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBhbmRyb2lkX2xheW91dF9ncmF2aXR5PzogbnVtYmVyO1xuICAgIGxheW91dF9hbmNob3I/OiBzdHJpbmc7XG4gICAgbGF5b3V0X2FuY2hvckdyYXZpdHk/OiBudW1iZXIgfCBMYXlvdXRBbmNob3JHcmF2aXR5RmxhZ3NFbnVtW107XG4gICAgbGF5b3V0X2JlaGF2aW9yPzogc3RyaW5nO1xuICAgIGxheW91dF9kb2RnZUluc2V0RWRnZXM/OiBMYXlvdXREb2RnZUluc2V0RWRnZXNGbGFnc0VudW1bXTtcbiAgICBsYXlvdXRfaW5zZXRFZGdlPzogTGF5b3V0SW5zZXRFZGdlRW51bTtcbiAgICBsYXlvdXRfa2V5bGluZT86IG51bWJlcjtcbiAgfVxuICBleHBvcnQgZW51bSBMYXlvdXRBbmNob3JHcmF2aXR5RmxhZ3NFbnVtIHtcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBjZW50ZXIgPSAnY2VudGVyJyxcbiAgICBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsXG4gICAgY2VudGVyX3ZlcnRpY2FsID0gJ2NlbnRlcl92ZXJ0aWNhbCcsXG4gICAgY2xpcF9ob3Jpem9udGFsID0gJ2NsaXBfaG9yaXpvbnRhbCcsXG4gICAgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBmaWxsID0gJ2ZpbGwnLFxuICAgIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLFxuICAgIGZpbGxfdmVydGljYWwgPSAnZmlsbF92ZXJ0aWNhbCcsXG4gICAgbGVmdCA9ICdsZWZ0JyxcbiAgICByaWdodCA9ICdyaWdodCcsXG4gICAgc3RhcnQgPSAnc3RhcnQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0RG9kZ2VJbnNldEVkZ2VzRmxhZ3NFbnVtIHtcbiAgICBhbGwgPSAnYWxsJyxcbiAgICBib3R0b20gPSAnYm90dG9tJyxcbiAgICBlbmQgPSAnZW5kJyxcbiAgICBsZWZ0ID0gJ2xlZnQnLFxuICAgIG5vbmUgPSAnbm9uZScsXG4gICAgcmlnaHQgPSAncmlnaHQnLFxuICAgIHN0YXJ0ID0gJ3N0YXJ0JyxcbiAgICB0b3AgPSAndG9wJ1xuICB9XG4gIGV4cG9ydCBlbnVtIExheW91dEluc2V0RWRnZUVudW0ge1xuICAgIGJvdHRvbSA9ICdib3R0b20nLFxuICAgIGVuZCA9ICdlbmQnLFxuICAgIGxlZnQgPSAnbGVmdCcsXG4gICAgbm9uZSA9ICdub25lJyxcbiAgICByaWdodCA9ICdyaWdodCcsXG4gICAgc3RhcnQgPSAnc3RhcnQnLFxuICAgIHRvcCA9ICd0b3AnXG4gIH1cbn1cbi8vIGVsZW1lbnRzXG5leHBvcnQgY29uc3QgQ29vcmRpbmF0b3JMYXlvdXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBDb29yZGluYXRvcmxheW91dFR5cGVzLkNvb3JkaW5hdG9yTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8Q29vcmRpbmF0b3JsYXlvdXRUeXBlcy5Db29yZGluYXRvckxheW91dEF0dHJpYnV0ZXMsIExheW91dFBhcmFtQXR0cmlidXRlcz4gPT4ge1xuICByZXR1cm4gZWxlbWVudCgnY29vcmRpbmF0b3JMYXlvdXQnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59OyJdLCJ2ZXJzaW9uIjozfQ==