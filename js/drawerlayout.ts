/* generated @ 2018-11-10T13:55:17.289 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./drawerlayout.types.d.ts' />

export const DrawerLayout = (
  attributes?: DrawerlayoutTypes.DrawerLayoutAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<DrawerlayoutTypes.DrawerLayoutAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('drawerLayout', attributes, children);
};
