/* generated @ 2018-11-09T18:39:00.807 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./core.types.d.ts' />

export const NestedScrollView = (
  attributes?: CoreTypes.NestedScrollViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CoreTypes.NestedScrollViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('nestedScrollView', attributes, children);
};
export const ContentLoadingProgressBar = (
  attributes?: CoreTypes.ContentLoadingProgressBarAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CoreTypes.ContentLoadingProgressBarAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('contentLoadingProgressBar', attributes, children);
};