/* generated @ 2018-11-09T18:39:27.396 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./gridlayout.types.d.ts' />

export const GridlayoutGridLayout = (
  attributes?: GridlayoutTypes.GridLayoutAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<GridlayoutTypes.GridLayoutAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('gridlayout.gridLayout', attributes, children);
};