import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-12T13:41:46.149 */
export namespace SwiperefreshlayoutTypes {
  export interface SwipeRefreshLayoutAttributes extends MainTypes.ViewGroupAttributes {
    enabled?: boolean;
  }
}
// elements
export const SwipeRefreshLayout = (
  attributes?: SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes, LayoutParamAttributes> => {
  return element('swipeRefreshLayout', attributes, children);
};