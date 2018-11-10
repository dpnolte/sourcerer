/* generated @ 2018-11-10T13:56:04.791 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./swiperefreshlayout.types.d.ts' />

export const SwipeRefreshLayout = (
  attributes?: SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<SwiperefreshlayoutTypes.SwipeRefreshLayoutAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('swipeRefreshLayout', attributes, children);
};
