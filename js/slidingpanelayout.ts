/* generated @ 2018-11-09T15:28:44.689 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const SlidingPaneLayout = (
  attributes?: SlidingpanelayoutTypes.SlidingPaneLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<SlidingpanelayoutTypes.SlidingPaneLayoutAttributes, LayoutParamAttributes> => {
  return element('slidingPaneLayout', attributes, children);
};