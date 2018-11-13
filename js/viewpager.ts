import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:11:59.102 */
export namespace ViewpagerTypes {
  export enum GravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum LayoutGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    clip_horizontal = 'clip_horizontal',
    clip_vertical = 'clip_vertical',
    end = 'end',
    fill = 'fill',
    fill_horizontal = 'fill_horizontal',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export interface PagerTabStripAttributes extends PagerTitleStripAttributes {
  }
  export interface PagerTitleStripAttributes extends MainTypes.ViewGroupAttributes {
    gravity?: number | GravityFlagsEnum[];
    textSize?: string;
  }
  export interface ViewPagerAttributes extends MainTypes.ViewGroupAttributes {
  }
  export interface ViewPagerLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
    layout_gravity?: number | LayoutGravityFlagsEnum[];
  }
}
// elements
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
