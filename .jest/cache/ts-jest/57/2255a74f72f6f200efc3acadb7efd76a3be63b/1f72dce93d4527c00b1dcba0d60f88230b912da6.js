"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T11:47:41.142 */
var DrawerlayoutTypes;
(function (DrawerlayoutTypes) {
    var LayoutGravityFlagsEnum;
    (function (LayoutGravityFlagsEnum) {
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["top"] = 0] = "top";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["bottom"] = 1] = "bottom";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["left"] = 2] = "left";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["right"] = 3] = "right";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["center_vertical"] = 4] = "center_vertical";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["fill_vertical"] = 5] = "fill_vertical";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["center_horizontal"] = 6] = "center_horizontal";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["fill_horizontal"] = 7] = "fill_horizontal";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["center"] = 8] = "center";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["fill"] = 9] = "fill";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["clip_vertical"] = 10] = "clip_vertical";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["clip_horizontal"] = 11] = "clip_horizontal";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["start"] = 12] = "start";
        LayoutGravityFlagsEnum[LayoutGravityFlagsEnum["end"] = 13] = "end";
    })(LayoutGravityFlagsEnum = DrawerlayoutTypes.LayoutGravityFlagsEnum || (DrawerlayoutTypes.LayoutGravityFlagsEnum = {}));
})(DrawerlayoutTypes = exports.DrawerlayoutTypes || (exports.DrawerlayoutTypes = {}));
// elements
exports.DrawerLayout = function (attributes, children) {
    return element_1.element('drawerLayout', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvZHJhd2VybGF5b3V0LnRzIiwibWFwcGluZ3MiOiI7O0FBQUEscUNBQWlEO0FBR2pELFFBQVE7QUFDUix5Q0FBeUM7QUFDekMsSUFBaUIsaUJBQWlCLENBUWpDO0FBUkQsV0FBaUIsaUJBQWlCO0lBT2hDLElBQVksc0JBQTZNO0lBQXpOLFdBQVksc0JBQXNCO1FBQUcsaUVBQUssQ0FBQTtRQUFFLHVFQUFRLENBQUE7UUFBRSxtRUFBTSxDQUFBO1FBQUUscUVBQU8sQ0FBQTtRQUFFLHlGQUFpQixDQUFBO1FBQUUscUZBQWUsQ0FBQTtRQUFFLDZGQUFtQixDQUFBO1FBQUUseUZBQWlCLENBQUE7UUFBRSx1RUFBUSxDQUFBO1FBQUUsbUVBQU0sQ0FBQTtRQUFFLHNGQUFlLENBQUE7UUFBRSwwRkFBaUIsQ0FBQTtRQUFFLHNFQUFPLENBQUE7UUFBRSxrRUFBSyxDQUFBO0lBQUMsQ0FBQyxFQUE3TSxzQkFBc0IsR0FBdEIsd0NBQXNCLEtBQXRCLHdDQUFzQixRQUF1TDtBQUMzTixDQUFDLEVBUmdCLGlCQUFpQixHQUFqQix5QkFBaUIsS0FBakIseUJBQWlCLFFBUWpDO0FBQ0QsV0FBVztBQUNFLFFBQUEsWUFBWSxHQUFHLFVBQzFCLFVBQTZFLEVBQzdFLFFBQTZEO0lBRTdELE9BQU8saUJBQU8sQ0FBQyxjQUFjLEVBQUUsVUFBVSxFQUFFLFFBQVEsQ0FBQyxDQUFDO0FBQ3ZELENBQUMsQ0FBQyIsIm5hbWVzIjpbXSwic291cmNlcyI6WyIvVXNlcnMvZGVyay9qc1Byb2plY3RzL3NvdXJjZXJlci9qcy9kcmF3ZXJsYXlvdXQudHMiXSwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgRWxlbWVudE5vZGUsIGVsZW1lbnQgfSBmcm9tICcuL2VsZW1lbnQnO1xuaW1wb3J0IHsgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzIH0gZnJvbSAnLi9sYXlvdXRwYXJhbXMnO1xuaW1wb3J0IHsgTWFpblR5cGVzIH0gZnJvbSBcIi4vbWFpblwiO1xuLy8gdHlwZXNcbi8qIGdlbmVyYXRlZCBAIDIwMTgtMTEtMTJUMTE6NDc6NDEuMTQyICovXG5leHBvcnQgbmFtZXNwYWNlIERyYXdlcmxheW91dFR5cGVzIHtcbiAgZXhwb3J0IGludGVyZmFjZSBEcmF3ZXJMYXlvdXRBdHRyaWJ1dGVzIGV4dGVuZHMgTWFpblR5cGVzLlZpZXdHcm91cEF0dHJpYnV0ZXMge1xuICAgIGNvbG9yUHJpbWFyeURhcms/OiBzdHJpbmc7XG4gIH1cbiAgZXhwb3J0IGludGVyZmFjZSBEcmF3ZXJMYXlvdXRMYXlvdXRQYXJhbXNBdHRyaWJ1dGVzIGV4dGVuZHMgTWFpblR5cGVzLlZpZXdHcm91cE1hcmdpbkxheW91dFBhcmFtc0F0dHJpYnV0ZXMge1xuICAgIGxheW91dF9ncmF2aXR5PzogbnVtYmVyIHwgTGF5b3V0R3Jhdml0eUZsYWdzRW51bVtdO1xuICB9XG4gIGV4cG9ydCBlbnVtIExheW91dEdyYXZpdHlGbGFnc0VudW0geyAndG9wJywgJ2JvdHRvbScsICdsZWZ0JywgJ3JpZ2h0JywgJ2NlbnRlcl92ZXJ0aWNhbCcsICdmaWxsX3ZlcnRpY2FsJywgJ2NlbnRlcl9ob3Jpem9udGFsJywgJ2ZpbGxfaG9yaXpvbnRhbCcsICdjZW50ZXInLCAnZmlsbCcsICdjbGlwX3ZlcnRpY2FsJywgJ2NsaXBfaG9yaXpvbnRhbCcsICdzdGFydCcsICdlbmQnIH1cbn1cbi8vIGVsZW1lbnRzXG5leHBvcnQgY29uc3QgRHJhd2VyTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogRHJhd2VybGF5b3V0VHlwZXMuRHJhd2VyTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8RHJhd2VybGF5b3V0VHlwZXMuRHJhd2VyTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdkcmF3ZXJMYXlvdXQnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59OyJdLCJ2ZXJzaW9uIjozfQ==