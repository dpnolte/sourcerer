"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T13:29:55.972 */
var DrawerlayoutTypes;
(function (DrawerlayoutTypes) {
    var LayoutGravityFlagsEnum;
    (function (LayoutGravityFlagsEnum) {
        LayoutGravityFlagsEnum["bottom"] = "bottom";
        LayoutGravityFlagsEnum["center"] = "center";
        LayoutGravityFlagsEnum["center_horizontal"] = "center_horizontal";
        LayoutGravityFlagsEnum["center_vertical"] = "center_vertical";
        LayoutGravityFlagsEnum["clip_horizontal"] = "clip_horizontal";
        LayoutGravityFlagsEnum["clip_vertical"] = "clip_vertical";
        LayoutGravityFlagsEnum["end"] = "end";
        LayoutGravityFlagsEnum["fill"] = "fill";
        LayoutGravityFlagsEnum["fill_horizontal"] = "fill_horizontal";
        LayoutGravityFlagsEnum["fill_vertical"] = "fill_vertical";
        LayoutGravityFlagsEnum["left"] = "left";
        LayoutGravityFlagsEnum["right"] = "right";
        LayoutGravityFlagsEnum["start"] = "start";
        LayoutGravityFlagsEnum["top"] = "top";
    })(LayoutGravityFlagsEnum = DrawerlayoutTypes.LayoutGravityFlagsEnum || (DrawerlayoutTypes.LayoutGravityFlagsEnum = {}));
})(DrawerlayoutTypes = exports.DrawerlayoutTypes || (exports.DrawerlayoutTypes = {}));
// elements
exports.DrawerLayout = function (attributes, children) {
    return element_1.element('drawerLayout', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvZHJhd2VybGF5b3V0LnRzIiwibWFwcGluZ3MiOiI7O0FBQUEscUNBQWlEO0FBR2pELFFBQVE7QUFDUix5Q0FBeUM7QUFDekMsSUFBaUIsaUJBQWlCLENBUWpDO0FBUkQsV0FBaUIsaUJBQWlCO0lBT2hDLElBQVksc0JBQW1YO0lBQS9YLFdBQVksc0JBQXNCO1FBQUcsMkNBQWlCLENBQUE7UUFBRSwyQ0FBaUIsQ0FBQTtRQUFFLGlFQUF1QyxDQUFBO1FBQUUsNkRBQW1DLENBQUE7UUFBRSw2REFBbUMsQ0FBQTtRQUFFLHlEQUErQixDQUFBO1FBQUUscUNBQVcsQ0FBQTtRQUFFLHVDQUFhLENBQUE7UUFBRSw2REFBbUMsQ0FBQTtRQUFFLHlEQUErQixDQUFBO1FBQUUsdUNBQWEsQ0FBQTtRQUFFLHlDQUFlLENBQUE7UUFBRSx5Q0FBZSxDQUFBO1FBQUUscUNBQVcsQ0FBQTtJQUFDLENBQUMsRUFBblgsc0JBQXNCLEdBQXRCLHdDQUFzQixLQUF0Qix3Q0FBc0IsUUFBNlY7QUFDalksQ0FBQyxFQVJnQixpQkFBaUIsR0FBakIseUJBQWlCLEtBQWpCLHlCQUFpQixRQVFqQztBQUNELFdBQVc7QUFDRSxRQUFBLFlBQVksR0FBRyxVQUMxQixVQUE2RSxFQUM3RSxRQUE2RDtJQUU3RCxPQUFPLGlCQUFPLENBQUMsY0FBYyxFQUFFLFVBQVUsRUFBRSxRQUFRLENBQUMsQ0FBQztBQUN2RCxDQUFDLENBQUMiLCJuYW1lcyI6W10sInNvdXJjZXMiOlsiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvZHJhd2VybGF5b3V0LnRzIl0sInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IEVsZW1lbnROb2RlLCBlbGVtZW50IH0gZnJvbSAnLi9lbGVtZW50JztcbmltcG9ydCB7IExheW91dFBhcmFtQXR0cmlidXRlcyB9IGZyb20gJy4vbGF5b3V0cGFyYW1zJztcbmltcG9ydCB7IE1haW5UeXBlcyB9IGZyb20gXCIuL21haW5cIjtcbi8vIHR5cGVzXG4vKiBnZW5lcmF0ZWQgQCAyMDE4LTExLTEyVDEzOjI5OjU1Ljk3MiAqL1xuZXhwb3J0IG5hbWVzcGFjZSBEcmF3ZXJsYXlvdXRUeXBlcyB7XG4gIGV4cG9ydCBpbnRlcmZhY2UgRHJhd2VyTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIE1haW5UeXBlcy5WaWV3R3JvdXBBdHRyaWJ1dGVzIHtcbiAgICBjb2xvclByaW1hcnlEYXJrPzogc3RyaW5nO1xuICB9XG4gIGV4cG9ydCBpbnRlcmZhY2UgRHJhd2VyTGF5b3V0TGF5b3V0UGFyYW1zQXR0cmlidXRlcyBleHRlbmRzIE1haW5UeXBlcy5WaWV3R3JvdXBNYXJnaW5MYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIHtcbiAgICBsYXlvdXRfZ3Jhdml0eT86IG51bWJlciB8IExheW91dEdyYXZpdHlGbGFnc0VudW1bXTtcbiAgfVxuICBleHBvcnQgZW51bSBMYXlvdXRHcmF2aXR5RmxhZ3NFbnVtIHsgYm90dG9tID0gJ2JvdHRvbScsIGNlbnRlciA9ICdjZW50ZXInLCBjZW50ZXJfaG9yaXpvbnRhbCA9ICdjZW50ZXJfaG9yaXpvbnRhbCcsIGNlbnRlcl92ZXJ0aWNhbCA9ICdjZW50ZXJfdmVydGljYWwnLCBjbGlwX2hvcml6b250YWwgPSAnY2xpcF9ob3Jpem9udGFsJywgY2xpcF92ZXJ0aWNhbCA9ICdjbGlwX3ZlcnRpY2FsJywgZW5kID0gJ2VuZCcsIGZpbGwgPSAnZmlsbCcsIGZpbGxfaG9yaXpvbnRhbCA9ICdmaWxsX2hvcml6b250YWwnLCBmaWxsX3ZlcnRpY2FsID0gJ2ZpbGxfdmVydGljYWwnLCBsZWZ0ID0gJ2xlZnQnLCByaWdodCA9ICdyaWdodCcsIHN0YXJ0ID0gJ3N0YXJ0JywgdG9wID0gJ3RvcCcgfVxufVxuLy8gZWxlbWVudHNcbmV4cG9ydCBjb25zdCBEcmF3ZXJMYXlvdXQgPSAoXG4gIGF0dHJpYnV0ZXM/OiBEcmF3ZXJsYXlvdXRUeXBlcy5EcmF3ZXJMYXlvdXRBdHRyaWJ1dGVzICYgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzLFxuICBjaGlsZHJlbj86IEFycmF5PEVsZW1lbnROb2RlPHVua25vd24sIExheW91dFBhcmFtQXR0cmlidXRlcz4+XG4pOiBFbGVtZW50Tm9kZTxEcmF3ZXJsYXlvdXRUeXBlcy5EcmF3ZXJMYXlvdXRBdHRyaWJ1dGVzLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+ID0+IHtcbiAgcmV0dXJuIGVsZW1lbnQoJ2RyYXdlckxheW91dCcsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbn07Il0sInZlcnNpb24iOjN9