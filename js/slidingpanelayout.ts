/* generated @ 2018-11-09T18:39:20.488 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./slidingpanelayout.types.d.ts' />

export const SlidingPaneLayout = (
  attributes?: SlidingpanelayoutTypes.SlidingPaneLayoutAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<SlidingpanelayoutTypes.SlidingPaneLayoutAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('slidingPaneLayout', attributes, children);
};