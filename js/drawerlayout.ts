import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:53:33.845 */
export namespace DrawerlayoutTypes {
  export interface DrawerLayoutAttributes extends MainTypes.ViewGroupAttributes {
    colorPrimaryDark?: string;
  }
  export interface DrawerLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: number | LayoutGravityFlagsEnum[];
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
}
// elements
export const DrawerLayout = (
  attributes?: DrawerlayoutTypes.DrawerLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<DrawerlayoutTypes.DrawerLayoutAttributes, LayoutParamAttributes> => {
  return element('drawerLayout', attributes, children);
};
