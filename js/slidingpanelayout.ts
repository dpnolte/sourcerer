import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T12:44:24.143 */
export namespace SlidingpanelayoutTypes {
  export interface SlidingPaneLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }
  export interface SlidingPaneLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    layout_weight?: number;
  }
}
// elements
export const SlidingPaneLayout = (
  attributes?: SlidingpanelayoutTypes.SlidingPaneLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<SlidingpanelayoutTypes.SlidingPaneLayoutAttributes, LayoutParamAttributes> => {
  return element('slidingPaneLayout', attributes, children);
};