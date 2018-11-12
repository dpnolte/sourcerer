"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.element = function (type, providedAttributes, providedChildren) {
    var attributes = typeof providedAttributes !== 'undefined' ? providedAttributes : {};
    var children = typeof providedChildren !== 'undefined' ? providedChildren : [];
    var buildRoot = function () {
        var map = flatMapNodes(type, attributes, children);
        return { map: map, toJson: function () { return mapToJson(map); } };
    };
    return {
        attributes: attributes,
        children: children,
        type: type,
        build: buildRoot,
    };
};
var flatMapNodes = function (type, attributes, childElements, map, keyPrefix) {
    if (map === void 0) { map = {}; }
    if (keyPrefix === void 0) { keyPrefix = ''; }
    var key = keyPrefix !== '' ? keyPrefix + "_" + type : type;
    var children = [];
    map[key] = {
        id: key,
        type: type,
        attributes: attributes,
        children: children,
    };
    childElements.forEach(function (childElement) {
        var childKey = key + "_" + childElement.type;
        children.push(childKey);
        flatMapNodes(childElement.type, childElement.attributes, childElement.children, map, key);
    });
    return map;
};
// from object to json array
var mapToJson = function (map) {
    var list = Object.keys(map).map(function (key) { return map[key]; });
    return JSON.stringify(list);
};
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJmaWxlIjoiL1VzZXJzL2RlcmsvanNQcm9qZWN0cy9zb3VyY2VyZXIvanMvZWxlbWVudC50cyIsIm1hcHBpbmdzIjoiOztBQW1CYSxRQUFBLE9BQU8sR0FBRyxVQUNyQixJQUFZLEVBQ1osa0JBQXNELEVBQ3RELGdCQUF1RDtJQUV2RCxJQUFNLFVBQVUsR0FBRyxPQUFPLGtCQUFrQixLQUFLLFdBQVcsQ0FBQyxDQUFDLENBQUMsa0JBQWtCLENBQUMsQ0FBQyxDQUFDLEVBQUUsQ0FBQztJQUN2RixJQUFNLFFBQVEsR0FBRyxPQUFPLGdCQUFnQixLQUFLLFdBQVcsQ0FBQyxDQUFDLENBQUMsZ0JBQWdCLENBQUMsQ0FBQyxDQUFDLEVBQUUsQ0FBQztJQUNqRixJQUFNLFNBQVMsR0FBRztRQUNoQixJQUFNLEdBQUcsR0FBRyxZQUFZLENBQUMsSUFBSSxFQUFFLFVBQVUsRUFBRSxRQUFRLENBQUMsQ0FBQztRQUNyRCxPQUFPLEVBQUUsR0FBRyxLQUFBLEVBQUUsTUFBTSxFQUFFLGNBQU0sT0FBQSxTQUFTLENBQUMsR0FBRyxDQUFDLEVBQWQsQ0FBYyxFQUFFLENBQUM7SUFDL0MsQ0FBQyxDQUFDO0lBQ0YsT0FBTztRQUNKLFVBQVUsWUFBQTtRQUNWLFFBQVEsVUFBQTtRQUNSLElBQUksTUFBQTtRQUNKLEtBQUssRUFBRSxTQUFTO0tBQ2xCLENBQUM7QUFDSixDQUFDLENBQUM7QUFFRixJQUFNLFlBQVksR0FBRyxVQUNuQixJQUFZLEVBQ1osVUFBZSxFQUNmLGFBQW9ELEVBQ3BELEdBQW9CLEVBQ3BCLFNBQXNCO0lBRHRCLG9CQUFBLEVBQUEsUUFBb0I7SUFDcEIsMEJBQUEsRUFBQSxjQUFzQjtJQUV0QixJQUFNLEdBQUcsR0FBRyxTQUFTLEtBQUssRUFBRSxDQUFDLENBQUMsQ0FBSSxTQUFTLFNBQUksSUFBTSxDQUFDLENBQUMsQ0FBQyxJQUFJLENBQUM7SUFDN0QsSUFBTSxRQUFRLEdBQVUsRUFBRSxDQUFDO0lBQzNCLEdBQUcsQ0FBQyxHQUFHLENBQUMsR0FBSTtRQUNWLEVBQUUsRUFBRSxHQUFHO1FBQ1AsSUFBSSxNQUFBO1FBQ0osVUFBVSxZQUFBO1FBQ1YsUUFBUSxVQUFBO0tBQ1QsQ0FBQztJQUNGLGFBQWEsQ0FBQyxPQUFPLENBQUMsVUFBQSxZQUFZO1FBQ2hDLElBQU0sUUFBUSxHQUFNLEdBQUcsU0FBSSxZQUFZLENBQUMsSUFBZ0IsQ0FBQTtRQUN4RCxRQUFRLENBQUMsSUFBSSxDQUFDLFFBQVEsQ0FBQyxDQUFDO1FBQ3hCLFlBQVksQ0FDVixZQUFZLENBQUMsSUFBSSxFQUNqQixZQUFZLENBQUMsVUFBVSxFQUN2QixZQUFZLENBQUMsUUFBUSxFQUNyQixHQUFHLEVBQ0gsR0FBRyxDQUNKLENBQUM7SUFDSixDQUFDLENBQUMsQ0FBQztJQUNILE9BQU8sR0FBRyxDQUFDO0FBQ2IsQ0FBQyxDQUFDO0FBRUYsNEJBQTRCO0FBQzVCLElBQU0sU0FBUyxHQUFHLFVBQUMsR0FBUTtJQUN6QixJQUFNLElBQUksR0FBRyxNQUFNLENBQUMsSUFBSSxDQUFDLEdBQUcsQ0FBQyxDQUFDLEdBQUcsQ0FBRSxVQUFBLEdBQUcsSUFBSSxPQUFBLEdBQUcsQ0FBQyxHQUFHLENBQUMsRUFBUixDQUFRLENBQUMsQ0FBQztJQUNwRCxPQUFPLElBQUksQ0FBQyxTQUFTLENBQUMsSUFBSSxDQUFDLENBQUM7QUFDOUIsQ0FBQyxDQUFDIiwibmFtZXMiOltdLCJzb3VyY2VzIjpbIi9Vc2Vycy9kZXJrL2pzUHJvamVjdHMvc291cmNlcmVyL2pzL2VsZW1lbnQudHMiXSwic291cmNlc0NvbnRlbnQiOlsiZXhwb3J0IGludGVyZmFjZSBFbGVtZW50Tm9kZTxWaWV3QXR0cmlidXRlcywgTGF5b3V0QXR0cmlidXRlcz4ge1xuICBhdHRyaWJ1dGVzOiBWaWV3QXR0cmlidXRlcyAmIExheW91dEF0dHJpYnV0ZXMgfCB7fTtcbiAgdHlwZTogc3RyaW5nO1xuICBjaGlsZHJlbjogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgdW5rbm93bj4+O1xuICBidWlsZDogKCkgPT4gTGF5b3V0TWFwO1xufVxuZXhwb3J0IGludGVyZmFjZSBFbGVtZW50TWFwIHtcbiAgW2tleTogc3RyaW5nXTogVmlld0Jhc2U8dW5rbm93biwgdW5rbm93bj5cbn1cbmV4cG9ydCBpbnRlcmZhY2UgTGF5b3V0TWFwIHtcbiAgbWFwOiBFbGVtZW50TWFwO1xuICB0b0pzb246ICgpID0+IHN0cmluZztcbn1cbmV4cG9ydCBpbnRlcmZhY2UgVmlld0Jhc2U8Vmlld0F0dHJpYnV0ZXMsIExheW91dEF0dHJpYnV0ZXMgPSB7fT4ge1xuICBpZDogc3RyaW5nO1xuICBhdHRyaWJ1dGVzOiBWaWV3QXR0cmlidXRlcyAmIExheW91dEF0dHJpYnV0ZXM7XG4gIHR5cGU6IHN0cmluZztcbiAgY2hpbGRyZW4/OiBBcnJheTxTdHJpbmc+O1xufVxuZXhwb3J0IGNvbnN0IGVsZW1lbnQgPSA8Vmlld0F0dHJpYnV0ZXMgZXh0ZW5kcyB7fSwgTGF5b3V0QXR0cmlidXRlcyBleHRlbmRzIHt9PiAoXG4gIHR5cGU6IHN0cmluZyxcbiAgcHJvdmlkZWRBdHRyaWJ1dGVzPzogVmlld0F0dHJpYnV0ZXMgJiBMYXlvdXRBdHRyaWJ1dGVzLFxuICBwcm92aWRlZENoaWxkcmVuPzogQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgdW5rbm93bj4+XG4pOiBFbGVtZW50Tm9kZTxWaWV3QXR0cmlidXRlcywgTGF5b3V0QXR0cmlidXRlcz4gID0+IHtcbiAgY29uc3QgYXR0cmlidXRlcyA9IHR5cGVvZiBwcm92aWRlZEF0dHJpYnV0ZXMgIT09ICd1bmRlZmluZWQnID8gcHJvdmlkZWRBdHRyaWJ1dGVzIDoge307XG4gIGNvbnN0IGNoaWxkcmVuID0gdHlwZW9mIHByb3ZpZGVkQ2hpbGRyZW4gIT09ICd1bmRlZmluZWQnID8gcHJvdmlkZWRDaGlsZHJlbiA6IFtdO1xuICBjb25zdCBidWlsZFJvb3QgPSAoKTogTGF5b3V0TWFwICA9PiB7XG4gICAgY29uc3QgbWFwID0gZmxhdE1hcE5vZGVzKHR5cGUsIGF0dHJpYnV0ZXMsIGNoaWxkcmVuKTtcbiAgICByZXR1cm4geyBtYXAsIHRvSnNvbjogKCkgPT4gbWFwVG9Kc29uKG1hcCkgfTtcbiAgfTtcbiAgcmV0dXJuIHtcbiAgICAgYXR0cmlidXRlcyxcbiAgICAgY2hpbGRyZW4sXG4gICAgIHR5cGUsXG4gICAgIGJ1aWxkOiBidWlsZFJvb3QsXG4gIH07XG59O1xuXG5jb25zdCBmbGF0TWFwTm9kZXMgPSAoXG4gIHR5cGU6IHN0cmluZyxcbiAgYXR0cmlidXRlczogYW55LFxuICBjaGlsZEVsZW1lbnRzOiAgQXJyYXk8RWxlbWVudE5vZGU8dW5rbm93biwgdW5rbm93bj4+LFxuICBtYXA6IEVsZW1lbnRNYXAgPSB7fSxcbiAga2V5UHJlZml4OiBzdHJpbmcgPSAnJ1xuKTogRWxlbWVudE1hcCA9PiB7XG4gIGNvbnN0IGtleSA9IGtleVByZWZpeCAhPT0gJycgPyBgJHtrZXlQcmVmaXh9XyR7dHlwZX1gIDogdHlwZTtcbiAgY29uc3QgY2hpbGRyZW46IGFueVtdID0gW107XG4gIG1hcFtrZXldICA9IHtcbiAgICBpZDoga2V5LFxuICAgIHR5cGUsXG4gICAgYXR0cmlidXRlcyxcbiAgICBjaGlsZHJlbixcbiAgfTtcbiAgY2hpbGRFbGVtZW50cy5mb3JFYWNoKGNoaWxkRWxlbWVudCA9PiB7XG4gICAgY29uc3QgY2hpbGRLZXkgPSBgJHtrZXl9XyR7Y2hpbGRFbGVtZW50LnR5cGV9YCBhcyBTdHJpbmdcbiAgICBjaGlsZHJlbi5wdXNoKGNoaWxkS2V5KTtcbiAgICBmbGF0TWFwTm9kZXMoXG4gICAgICBjaGlsZEVsZW1lbnQudHlwZSxcbiAgICAgIGNoaWxkRWxlbWVudC5hdHRyaWJ1dGVzLFxuICAgICAgY2hpbGRFbGVtZW50LmNoaWxkcmVuLFxuICAgICAgbWFwLFxuICAgICAga2V5XG4gICAgKTtcbiAgfSk7XG4gIHJldHVybiBtYXA7XG59O1xuXG4vLyBmcm9tIG9iamVjdCB0byBqc29uIGFycmF5XG5jb25zdCBtYXBUb0pzb24gPSAobWFwOiBhbnkpOiBzdHJpbmcgPT4ge1xuICBjb25zdCBsaXN0ID0gT2JqZWN0LmtleXMobWFwKS5tYXAgKGtleSA9PiBtYXBba2V5XSk7XG4gIHJldHVybiBKU09OLnN0cmluZ2lmeShsaXN0KTtcbn07XG4iXSwidmVyc2lvbiI6M30=