/* generated @ 2018-11-09T15:28:29.828 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const CoordinatorLayout = (
  attributes?: CoordinatorlayoutTypes.CoordinatorLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CoordinatorlayoutTypes.CoordinatorLayoutAttributes, LayoutParamAttributes> => {
  return element('coordinatorLayout', attributes, children);
};