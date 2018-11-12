import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-12T14:27:21.299 */
export namespace CoreTypes {
  export interface ContentLoadingProgressBarAttributes extends MainTypes.ProgressBarAttributes {
  }
  export interface NestedScrollViewAttributes extends MainTypes.FrameLayoutAttributes {
    fillViewport?: boolean;
  }
}
// elements
export const NestedScrollView = (
  attributes?: CoreTypes.NestedScrollViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CoreTypes.NestedScrollViewAttributes, LayoutParamAttributes> => {
  return element('nestedScrollView', attributes, children);
};
export const ContentLoadingProgressBar = (
  attributes?: CoreTypes.ContentLoadingProgressBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CoreTypes.ContentLoadingProgressBarAttributes, LayoutParamAttributes> => {
  return element('contentLoadingProgressBar', attributes, children);
};