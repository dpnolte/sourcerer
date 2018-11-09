/* generated @ 2018-11-09T15:28:43.779 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const SwipeRefreshLayout = (
  attributes?: SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes, LayoutParamAttributes> => {
  return element('swipeRefreshLayout', attributes, children);
};