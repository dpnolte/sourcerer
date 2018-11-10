/* generated @ 2018-11-10T13:55:15.321 */
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
