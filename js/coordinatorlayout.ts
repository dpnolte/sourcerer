/* generated @ 2018-11-09T18:38:59.439 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./coordinatorlayout.types.d.ts' />

export const CoordinatorLayout = (
  attributes?: CoordinatorlayoutTypes.CoordinatorLayoutAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CoordinatorlayoutTypes.CoordinatorLayoutAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('coordinatorLayout', attributes, children);
};