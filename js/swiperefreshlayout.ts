import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-13T11:40:42.612 */
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