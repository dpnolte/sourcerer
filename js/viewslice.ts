/* generated @ 2018-11-09T15:28:38.202 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const SliceView = (
  attributes?: ViewSliceTypes.SliceViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewSliceTypes.SliceViewAttributes, LayoutParamAttributes> => {
  return element('sliceView', attributes, children);
};