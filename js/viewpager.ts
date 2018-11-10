/* generated @ 2018-11-10T13:56:03.380 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./viewpager.types.d.ts' />

export const PagerTabStrip = (
  attributes?: ViewpagerTypes.PagerTabStripAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<ViewpagerTypes.PagerTabStripAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('pagerTabStrip', attributes, children);
};
export const PagerTitleStrip = (
  attributes?: ViewpagerTypes.PagerTitleStripAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<ViewpagerTypes.PagerTitleStripAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('pagerTitleStrip', attributes, children);
};
export const ViewPager = (
  attributes?: ViewpagerTypes.ViewPagerAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<ViewpagerTypes.ViewPagerAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('viewPager', attributes, children);
};
