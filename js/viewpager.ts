/* generated @ 2018-11-09T15:28:42.655 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const PagerTabStrip = (
  attributes?: ViewpagerTypes.PagerTabStripAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewpagerTypes.PagerTabStripAttributes, LayoutParamAttributes> => {
  return element('pagerTabStrip', attributes, children);
};
export const PagerTitleStrip = (
  attributes?: ViewpagerTypes.PagerTitleStripAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewpagerTypes.PagerTitleStripAttributes, LayoutParamAttributes> => {
  return element('pagerTitleStrip', attributes, children);
};
export const ViewPager = (
  attributes?: ViewpagerTypes.ViewPagerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ViewpagerTypes.ViewPagerAttributes, LayoutParamAttributes> => {
  return element('viewPager', attributes, children);
};