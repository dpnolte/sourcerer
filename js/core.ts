/* generated @ 2018-11-09T15:28:30.891 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
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