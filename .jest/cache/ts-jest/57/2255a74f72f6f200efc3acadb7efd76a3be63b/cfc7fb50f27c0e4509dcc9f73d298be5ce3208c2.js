"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var element_1 = require("./element");
// types
/* generated @ 2018-11-12T11:47:41.142 */
var DrawerlayoutTypes;
(function (DrawerlayoutTypes) {
    var LayoutGravityFlagsEnum;
    (function (LayoutGravityFlagsEnum) {
        LayoutGravityFlagsEnum["left"] = "left";
        LayoutGravityFlagsEnum["start"] = "start";
    })(LayoutGravityFlagsEnum = DrawerlayoutTypes.LayoutGravityFlagsEnum || (DrawerlayoutTypes.LayoutGravityFlagsEnum = {}));
})(DrawerlayoutTypes = exports.DrawerlayoutTypes || (exports.DrawerlayoutTypes = {}));
// elements
exports.DrawerLayout = function (attributes, children) {
    return element_1.element('drawerLayout', attributes, children);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvZHJhd2VybGF5b3V0LnRzIiwibWFwcGluZ3MiOiI7O0FBQUEscUNBQWlEO0FBR2pELFFBQVE7QUFDUix5Q0FBeUM7QUFDekMsSUFBaUIsaUJBQWlCLENBUWpDO0FBUkQsV0FBaUIsaUJBQWlCO0lBT2hDLElBQVksc0JBQXFEO0lBQWpFLFdBQVksc0JBQXNCO1FBQUcsdUNBQVcsQ0FBQTtRQUFFLHlDQUFhLENBQUE7SUFBQyxDQUFDLEVBQXJELHNCQUFzQixHQUF0Qix3Q0FBc0IsS0FBdEIsd0NBQXNCLFFBQStCO0FBQ25FLENBQUMsRUFSZ0IsaUJBQWlCLEdBQWpCLHlCQUFpQixLQUFqQix5QkFBaUIsUUFRakM7QUFDRCxXQUFXO0FBQ0UsUUFBQSxZQUFZLEdBQUcsVUFDMUIsVUFBNkUsRUFDN0UsUUFBNkQ7SUFFN0QsT0FBTyxpQkFBTyxDQUFDLGNBQWMsRUFBRSxVQUFVLEVBQUUsUUFBUSxDQUFDLENBQUM7QUFDdkQsQ0FBQyxDQUFDIiwibmFtZXMiOltdLCJzb3VyY2VzIjpbIi9Vc2Vycy9kZXJrL2pzUHJvamVjdHMvc291cmNlcmVyL2pzL2RyYXdlcmxheW91dC50cyJdLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBFbGVtZW50Tm9kZSwgZWxlbWVudCB9IGZyb20gJy4vZWxlbWVudCc7XG5pbXBvcnQgeyBMYXlvdXRQYXJhbUF0dHJpYnV0ZXMgfSBmcm9tICcuL2xheW91dHBhcmFtcyc7XG5pbXBvcnQgeyBNYWluVHlwZXMgfSBmcm9tIFwiLi9tYWluXCI7XG4vLyB0eXBlc1xuLyogZ2VuZXJhdGVkIEAgMjAxOC0xMS0xMlQxMTo0Nzo0MS4xNDIgKi9cbmV4cG9ydCBuYW1lc3BhY2UgRHJhd2VybGF5b3V0VHlwZXMge1xuICBleHBvcnQgaW50ZXJmYWNlIERyYXdlckxheW91dEF0dHJpYnV0ZXMgZXh0ZW5kcyBNYWluVHlwZXMuVmlld0dyb3VwQXR0cmlidXRlcyB7XG4gICAgY29sb3JQcmltYXJ5RGFyaz86IHN0cmluZztcbiAgfVxuICBleHBvcnQgaW50ZXJmYWNlIERyYXdlckxheW91dExheW91dFBhcmFtc0F0dHJpYnV0ZXMgZXh0ZW5kcyBNYWluVHlwZXMuVmlld0dyb3VwTWFyZ2luTGF5b3V0UGFyYW1zQXR0cmlidXRlcyB7XG4gICAgbGF5b3V0X2dyYXZpdHk/OiBudW1iZXIgfCBMYXlvdXRHcmF2aXR5RmxhZ3NFbnVtW107XG4gIH1cbiAgZXhwb3J0IGVudW0gTGF5b3V0R3Jhdml0eUZsYWdzRW51bSB7IGxlZnQ9J2xlZnQnLCBzdGFydD0nc3RhcnQnIH1cbn1cbi8vIGVsZW1lbnRzXG5leHBvcnQgY29uc3QgRHJhd2VyTGF5b3V0ID0gKFxuICBhdHRyaWJ1dGVzPzogRHJhd2VybGF5b3V0VHlwZXMuRHJhd2VyTGF5b3V0QXR0cmlidXRlcyAmIExheW91dFBhcmFtQXR0cmlidXRlcyxcbiAgY2hpbGRyZW4/OiBBcnJheTxFbGVtZW50Tm9kZTx1bmtub3duLCBMYXlvdXRQYXJhbUF0dHJpYnV0ZXM+PlxuKTogRWxlbWVudE5vZGU8RHJhd2VybGF5b3V0VHlwZXMuRHJhd2VyTGF5b3V0QXR0cmlidXRlcywgTGF5b3V0UGFyYW1BdHRyaWJ1dGVzPiA9PiB7XG4gIHJldHVybiBlbGVtZW50KCdkcmF3ZXJMYXlvdXQnLCBhdHRyaWJ1dGVzLCBjaGlsZHJlbik7XG59OyJdLCJ2ZXJzaW9uIjozfQ==