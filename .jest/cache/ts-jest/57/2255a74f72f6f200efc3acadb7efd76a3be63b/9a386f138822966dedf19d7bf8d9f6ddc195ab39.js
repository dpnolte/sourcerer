"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T11:47:38.559 */
var CoordinatorlayoutTypes;
(function (CoordinatorlayoutTypes) {
    var LayoutAnchorGravityFlagsEnum;
    (function (LayoutAnchorGravityFlagsEnum) {
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["top"] = 0] = "top";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["bottom"] = 1] = "bottom";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["left"] = 2] = "left";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["right"] = 3] = "right";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["center_vertical"] = 4] = "center_vertical";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["fill_vertical"] = 5] = "fill_vertical";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["center_horizontal"] = 6] = "center_horizontal";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["fill_horizontal"] = 7] = "fill_horizontal";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["center"] = 8] = "center";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["fill"] = 9] = "fill";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["clip_vertical"] = 10] = "clip_vertical";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["clip_horizontal"] = 11] = "clip_horizontal";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["start"] = 12] = "start";
        LayoutAnchorGravityFlagsEnum[LayoutAnchorGravityFlagsEnum["end"] = 13] = "end";
    })(LayoutAnchorGravityFlagsEnum = CoordinatorlayoutTypes.LayoutAnchorGravityFlagsEnum || (CoordinatorlayoutTypes.LayoutAnchorGravityFlagsEnum = {}));
    var LayoutDodgeInsetEdgesFlagsEnum;
    (function (LayoutDodgeInsetEdgesFlagsEnum) {
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["none"] = 0] = "none";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["top"] = 1] = "top";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["bottom"] = 2] = "bottom";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["left"] = 3] = "left";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["right"] = 4] = "right";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["start"] = 5] = "start";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["end"] = 6] = "end";
        LayoutDodgeInsetEdgesFlagsEnum[LayoutDodgeInsetEdgesFlagsEnum["all"] = 7] = "all";
    })(LayoutDodgeInsetEdgesFlagsEnum = CoordinatorlayoutTypes.LayoutDodgeInsetEdgesFlagsEnum || (CoordinatorlayoutTypes.LayoutDodgeInsetEdgesFlagsEnum = {}));
    var LayoutInsetEdgeEnum;
    (function (LayoutInsetEdgeEnum) {
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["none"] = 0] = "none";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["top"] = 1] = "top";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["bottom"] = 2] = "bottom";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["left"] = 3] = "left";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["right"] = 4] = "right";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["start"] = 5] = "start";
        LayoutInsetEdgeEnum[LayoutInsetEdgeEnum["end"] = 6] = "end";
    })(LayoutInsetEdgeEnum = CoordinatorlayoutTypes.LayoutInsetEdgeEnum || (CoordinatorlayoutTypes.LayoutInsetEdgeEnum = {}));
})(CoordinatorlayoutTypes = exports.CoordinatorlayoutTypes || (exports.CoordinatorlayoutTypes = {}));
// elements
exports.CoordinatorLayout = function (attributes, children) {
    return element_1.element('coordinatorLayout', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvY29vcmRpbmF0b3JsYXlvdXQudHMiLCJtYXBwaW5ncyI6Ijs7QUFBQSxxQ0FBaUQ7QUFHakQsUUFBUTtBQUNSLHlDQUF5QztBQUN6QyxJQUFpQixzQkFBc0IsQ0FldEM7QUFmRCxXQUFpQixzQkFBc0I7SUFZckMsSUFBWSw0QkFBbU47SUFBL04sV0FBWSw0QkFBNEI7UUFBRyw2RUFBSyxDQUFBO1FBQUUsbUZBQVEsQ0FBQTtRQUFFLCtFQUFNLENBQUE7UUFBRSxpRkFBTyxDQUFBO1FBQUUscUdBQWlCLENBQUE7UUFBRSxpR0FBZSxDQUFBO1FBQUUseUdBQW1CLENBQUE7UUFBRSxxR0FBaUIsQ0FBQTtRQUFFLG1GQUFRLENBQUE7UUFBRSwrRUFBTSxDQUFBO1FBQUUsa0dBQWUsQ0FBQTtRQUFFLHNHQUFpQixDQUFBO1FBQUUsa0ZBQU8sQ0FBQTtRQUFFLDhFQUFLLENBQUE7SUFBQyxDQUFDLEVBQW5OLDRCQUE0QixHQUE1QixtREFBNEIsS0FBNUIsbURBQTRCLFFBQXVMO0lBQy9OLElBQVksOEJBQWtHO0lBQTlHLFdBQVksOEJBQThCO1FBQUcsbUZBQU0sQ0FBQTtRQUFFLGlGQUFLLENBQUE7UUFBRSx1RkFBUSxDQUFBO1FBQUUsbUZBQU0sQ0FBQTtRQUFFLHFGQUFPLENBQUE7UUFBRSxxRkFBTyxDQUFBO1FBQUUsaUZBQUssQ0FBQTtRQUFFLGlGQUFLLENBQUE7SUFBQyxDQUFDLEVBQWxHLDhCQUE4QixHQUE5QixxREFBOEIsS0FBOUIscURBQThCLFFBQW9FO0lBQzlHLElBQVksbUJBQWdGO0lBQTVGLFdBQVksbUJBQW1CO1FBQUcsNkRBQU0sQ0FBQTtRQUFFLDJEQUFLLENBQUE7UUFBRSxpRUFBUSxDQUFBO1FBQUUsNkRBQU0sQ0FBQTtRQUFFLCtEQUFPLENBQUE7UUFBRSwrREFBTyxDQUFBO1FBQUUsMkRBQUssQ0FBQTtJQUFDLENBQUMsRUFBaEYsbUJBQW1CLEdBQW5CLDBDQUFtQixLQUFuQiwwQ0FBbUIsUUFBNkQ7QUFDOUYsQ0FBQyxFQWZnQixzQkFBc0IsR0FBdEIsOEJBQXNCLEtBQXRCLDhCQUFzQixRQWV0QztBQUNELFdBQVc7QUFDRSxRQUFBLGlCQUFpQixHQUFHLFVBQy9CLFVBQXVGLEVBQ3ZGLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxtQkFBbUIsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDNUQsQ0FBQyxDQUFDIiwibmFtZXMiOltdLCJzb3VyY2VzIjpbIi9Vc2Vycy9kZXJrL2pzUHJvamVjdHMvc291cmNlcmVyL2pzL2Nvb3JkaW5hdG9ybGF5b3V0LnRzIl0sInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IEVsZW1lbnROb2RlLCBlbGVtZW50IH0gZnJvbSAnLi9lbGVtZW50JztcbmltcG9ydCB7IExheW91dFBhcmFtQXR0cmlidXRlcyB9IGZyb20gJy4vbGF5b3V0cGFyYW1zJztcbmltcG9ydCB7IE1haW5UeXBlcyB9IGZyb20gXCIuL21haW5cIjtcbi8vIHR5cGVzXG4vKiBnZW5lcmF0ZWQgQCAyMDE4LTExLTEyVDExOjQ3OjM4LjU1OSAqL1xuZXhwb3J0IG5hbWVzcGFjZSBDb29yZGluYXRvcmxheW91dFR5cGVzIHtcbiAgZXhwb3J0IGludGVyZmFjZSBDb29yZGluYXRvckxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBNYWluVHlwZXMuVmlld0dyb3VwQXR0cmlidXRlcyB7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBDb29yZGluYXRvckxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBNYWluVHlwZXMuVmlld0dyb3VwTWFyZ2luTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gICAgYW5kcm9pZF9sYXlvdXRfZ3Jhdml0eT86IG51bWJlcjtcbiAgICBsYXlvdXRfYW5jaG9yPzogc3RyaW5nO1xuICAgIGxheW91dF9hbmNob3JHcmF2aXR5PzogbnVtYmVyIHwgTGF5b3V0QW5jaG9yR3Jhdml0eUZsYWdzRW51bVtdO1xuICAgIGxheW91dF9iZWhhdmlvcj86IHN0cmluZztcbiAgICBsYXlvdXRfZG9kZ2VJbnNldEVkZ2VzPzogTGF5b3V0RG9kZ2VJbnNldEVkZ2VzRmxhZ3NFbnVtW107XG4gICAgbGF5b3V0X2luc2V0RWRnZT86IExheW91dEluc2V0RWRnZUVudW07XG4gICAgbGF5b3V0X2tleWxpbmU/OiBudW1iZXI7XG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0QW5jaG9yR3Jhdml0eUZsYWdzRW51bSB7ICd0b3AnLCAnYm90dG9tJywgJ2xlZnQnLCAncmlnaHQnLCAnY2VudGVyX3ZlcnRpY2FsJywgJ2ZpbGxfdmVydGljYWwnLCAnY2VudGVyX2hvcml6b250YWwnLCAnZmlsbF9ob3Jpem9udGFsJywgJ2NlbnRlcicsICdmaWxsJywgJ2NsaXBfdmVydGljYWwnLCAnY2xpcF9ob3Jpem9udGFsJywgJ3N0YXJ0JywgJ2VuZCcgfVxuICBleHBvcnQgZW51bSBMYXlvdXREb2RnZUluc2V0RWRnZXNGbGFnc0VudW0geyAnbm9uZScsICd0b3AnLCAnYm90dG9tJywgJ2xlZnQnLCAncmlnaHQnLCAnc3RhcnQnLCAnZW5kJywgJ2FsbCcgfVxuICBleHBvcnQgZW51bSBMYXlvdXRJbnNldEVkZ2VFbnVtIHsgJ25vbmUnLCAndG9wJywgJ2JvdHRvbScsICdsZWZ0JywgJ3JpZ2h0JywgJ3N0YXJ0JywgJ2VuZCcgfVxufVxuLy8gZWxlbWVudHNcbmV4cG9ydCBjb25zdCBDb29yZGluYXRvckxheW91dCA9IChcbiAgYXR0cmlidXRlcz86IENvb3JkaW5hdG9ybGF5b3V0VHlwZXMuQ29vcmRpbmF0b3JMYXlvdXRBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxDb29yZGluYXRvcmxheW91dFR5cGVzLkNvb3JkaW5hdG9yTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdjb29yZGluYXRvckxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07Il0sInZlcnNpb24iOjN9